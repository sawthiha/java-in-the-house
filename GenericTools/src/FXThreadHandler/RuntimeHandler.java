/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXThreadHandler;

import javafx.application.Platform;

public class RuntimeHandler  {
	/**
	 * Run task and wait until it finished.
	 * 
	 * @param task to be done
	 */
	public static void runAndWait(Runnable task)  {
		FXThread thread = new FXThread(task, 1);
		
		if(Platform.isFxApplicationThread())  {
			thread.run();
			return;
		}

		Platform.runLater(thread);
		try  {
			thread.await();
		}catch(InterruptedException ex)  {
			ex.printStackTrace();
		}
	}
}