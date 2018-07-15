/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printjob;

import datamodel.Voucher;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author sawthiha
 */
public class JobChain implements Runnable  {
    /**
     * Constructor 
     * @param list of Vouchers
     * @param isSum Total Included
     */
    public JobChain(List<Voucher> list, boolean isSum)  {
        this.list = list;
        this.isSum = isSum;
        this.status = false;
    }
    
    @Override
    public void run() {
        Voucher v;
        FXPrintJob job;
        Iterator<Voucher> it = list.iterator();
        while(it.hasNext())  {
            this.status = true;
            v = it.next();
            job = JobFactory.getJob(v, this.isSum);
            job.run();
            this.status = this.status && job.getStatus();
        }
    }
    
    /**
     * Get Status
     * @return status in true or false
     */
    public boolean getStatus()  {
        return this.status;
    }
    
    private List<Voucher> list;
    private boolean isSum;
    private boolean status; 
}
