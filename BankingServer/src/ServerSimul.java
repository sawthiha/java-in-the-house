

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;
/**
 *
 * @author MIchael Pauk Sa
 */
public class ServerSimul implements Runnable {
    Thread t=null;
    Trunk trunk[]=new Trunk[20];
    ServerSocket s;
    int server_port=7896;
    /**
     *
     */
    public ServerSimul()  {
        try {
            s=new ServerSocket(server_port);
        }catch(IOException e)  {
            JOptionPane.showMessageDialog(null,e);
        }
        t=new Thread(this);
        t.setPriority(Thread.NORM_PRIORITY);
        t.start();
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted())  {
            for(int i=0;i<20;i++)  {
                if(trunk[i]==null)  {
                    try {
                        trunk[i]=new Trunk(s.accept());
                        break;
                    }catch(IOException e)  {
                       JOptionPane.showMessageDialog(null,"Server is down.Need immediate maintenence!");
                    }
                }
            }
        }
    }
}
class Trunk implements Runnable {
    Socket s;
    Thread t;
    ObjectInputStream oin;
    ObjectOutputStream oout;
    String userID;
    String type;
    public Trunk(Socket accepted)  {
        try {
            s=accepted;
            oin=new ObjectInputStream(s.getInputStream());
            oout=new ObjectOutputStream(s.getOutputStream());
            t=new Thread(this);
            t.setPriority(Thread.MAX_PRIORITY);
            t.start();
        }catch(IOException e)  {
            System.out.println(e);
            s=null;
        }
    }

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted())  {
                try {
                    InfoObject io=(InfoObject) oin.readObject();
                    infoProcessing(io);
                }catch(IOException e)  {
                    throw new InterruptedException("IOException Occured!");
                }catch(ClassNotFoundException e)  {
                    throw new InterruptedException("ClassNotFoundException Occured!");
                }
            }
        }catch(InterruptedException e)  {
            closeTrunk();
            userID=null;
            t=null;
        }
    }
    protected synchronized void infoProcessing(InfoObject io) throws InterruptedException  {
        if(io.type.equals("test"))
            acknowledgementProcess(io);   
        else if(io.type.equals("userLogin"))
            userLoginProcess(io);
        else if(io.type.equals("adminLogin")) 
            adminLoginProcess(io);
        else if(io.type.equals("closeAcc"))
            closeAcc(io);
        else if(io.type.equals("deposit")) 
            deposit(io);
        else if(io.type.equals("withdraw"))
            withdrawAcc(io);
        else if(io.type.equals("transfer"))
            transferAcc(io);
        else if(io.type.equals("checkAmt"))
            checkAmtProcess(io);
        else if(io.type.equals("block"))
            blockAccProcess(io);
        else if(io.type.equals("unblock"))
            unblockAccProcess(io);
        else if(io.type.equals("create"))
            createAccProcess(io);
        else if(io.type.equals("detail"))
            detailProcess(io);
        else if(io.type.equals("checkBlock"))
            checkBlockProcess(io);
        else if(io.type.equals("signOut"))   
            signOutProcess(io);
    }
    protected synchronized void acknowledgementProcess(InfoObject io) throws InterruptedException  {
        io.content="success";
        try  {
            oout.writeObject(io);
            oout.flush();
        }catch(IOException e)  {
            throw new InterruptedException("IOException");
        }
    }
    protected synchronized void userLoginProcess(InfoObject io) throws InterruptedException  {
        try {
        if(!MiscTools.accountVerification(io.userID,io.content))  {
            io.content="fail";
            oout.writeObject(io);
            oout.flush();
            return;
        }
        if(MiscTools.isBlocked(String.valueOf(MiscTools.getAccNo(io.userID))))  
            io.content="blocked";
        else
            io.content="success";
        io.detail=MiscTools.showDetail(io.userID);
        oout.writeObject(io);
        oout.flush();
        this.userID=io.userID;
        type="User";
        }catch(IOException e)  {
            throw new InterruptedException("IOException");
        }
    }
    protected synchronized void closeTrunk()  {
        try {
            oout.close();
            oin.close();
            s.close();
            s=null;
            if(userID!=null) 
                userID=null;
        }catch(IOException e)  {
        
        }
    }
    protected synchronized void adminLoginProcess(InfoObject io) throws InterruptedException  {
        try {
            if(!MiscTools.adminVerification(io.userID,io.content))  
                io.content="fail";
            else
                io.content="success";
            oout.writeObject(io);
            oout.flush();
            this.userID=io.userID;
            type="Admin";
        }catch(IOException e)  {
            throw new InterruptedException("IOException");
        }
    }
    protected synchronized void closeAcc(InfoObject io) throws InterruptedException  {
        try {
        if(MiscTools.closeAccount(io.content))  
            io.content="success";
        else
            io.content="fail";
        oout.writeObject(io);
        oout.flush();
        }catch(IOException e)  {
            throw new InterruptedException("IOException");
        }
    }
    protected synchronized void deposit(InfoObject io) throws InterruptedException  {
            String[] str=io.content.split(" ");
            try  {
            double amt=MiscTools.depositAcc(str[0],str[1]);
            if(amt==-1)  
                io.content="fail sql";
            else
            io.content=String.valueOf(amt); 
            oout.writeObject(io);
            oout.flush();
            }catch(IOException e)  {
                throw new InterruptedException("IOException");
            }
    }
    protected synchronized void withdrawAcc(InfoObject io) throws InterruptedException  {
        String[] str=io.content.split(" ");
        try {
            double amt=MiscTools.withdrawAcc(str[0],str[1]);
            if(amt==-1)  
                io.content="fail sql";
            else if(amt==-2)  
                io.content="fail amt";
            else
                io.content=String.valueOf(amt);
            oout.writeObject(io);
            oout.flush();
        }catch(IOException e) {
            throw new InterruptedException("IOException");
        }
    }
    protected synchronized void transferAcc(InfoObject io) throws InterruptedException  {
        String[] str=io.content.split(" ");
        try {
            double amt=MiscTools.transferAcc(str[0],str[1],str[2]);
            if(amt==-1) 
                io.content="fail sql";
            else if(amt==-2)  
                io.content="fail amt";
            else if(amt==-3)
                io.content="fail des";
            else if(amt==-4)
                io.content="blocked";
            else
                io.content=String.valueOf(amt);
            oout.writeObject(io);
            oout.flush();
        }catch(IOException e)  {
            throw new InterruptedException("IOException");
        }
    }
    protected synchronized void checkAmtProcess(InfoObject io) throws InterruptedException  {
        try {
        io.content=String.valueOf(MiscTools.checkAmt(io.content));
        oout.writeObject(io);
        oout.flush();
        }catch(IOException e)  {
            throw new InterruptedException("IOException");
        }
    }
    protected synchronized void blockAccProcess(InfoObject io) throws InterruptedException  {
        try {
            if(MiscTools.blockAcc(io.content))
                io.content="success";
            else
                io.content="fail";
            oout.writeObject(io);
            oout.flush();
        }catch(IOException e)  {
            throw new InterruptedException("IOException");
        }
    }
    
    protected synchronized void unblockAccProcess(InfoObject io) throws InterruptedException  {
        try {
            if(MiscTools.unblockAcc(io.content))
                io.content="success";
            else
                io.content="fail";
            oout.writeObject(io);
            oout.flush();
        }catch(IOException e)  {
            throw new InterruptedException("IOException");
        }
    }
    
    protected synchronized void createAccProcess(InfoObject io) throws InterruptedException  {
        try {
            String userID=AccCreation.createAcc(io.detail[0],io.detail[1],io.detail[2],io.detail[3],io.detail[4],io.detail[5],io.detail[6],io.detail[7],io.detail[8]);
            io.content=userID;
            oout.writeObject(io);
            oout.flush();
        }catch(IOException e)  {
            throw new InterruptedException("IOException");
        }
    }
    
    protected synchronized void detailProcess(InfoObject io) throws InterruptedException  {
        try {
            String[] str=MiscTools.showDetail(io.content);
            if(str==null)
                io.content="fail";
            else {
                io.detail=str;
                if(MiscTools.isBlocked(str[0]))
                    io.content="blocked";
            }
            oout.writeObject(io);
            oout.flush();
        }catch(IOException e)  {
            throw new InterruptedException("IOException");
        }
    }
    
    protected synchronized void checkBlockProcess(InfoObject io) throws InterruptedException  {
        if(MiscTools.isBlocked(String.valueOf(MiscTools.getAccNo(io.content))))
            io.content="blocked";
        else
            io.content="unblocked";
        try {
            oout.writeObject(io);
            oout.flush();
        }catch(IOException e)  {
            throw new InterruptedException("IOException");
        }
    }
    
    protected synchronized void signOutProcess(InfoObject io) throws InterruptedException  {
        try {
            oout.writeObject(io);
            oout.flush();
            throw new InterruptedException("signOut");
        }catch(IOException e) {
            throw new InterruptedException("IOException");
        }
    }
    
    @Override
    public String toString() {
        if(s==null)
            return null;
        else
            return "alive";
    }
}
