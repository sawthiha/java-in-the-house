/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printjob;

import datamodel.Voucher;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sawthiha
 */
public class JobFactory {
    /**
     * Get PrinterJob
     * @param voucher
     * @param isSum Total Included
     * @return FXPrinter object
     */
    public static FXPrintJob getJob(Voucher voucher, boolean isSum)  {
        return new FXPrintJob(voucher, isSum);
    }
    
    /**
     * Get Job Chain
     * @param list of Vouchers
     * @param isSum Total Included
     * @return JobChain Object
     */
    public static JobChain getChain(List<Voucher> list, boolean isSum)  {
        return new JobChain(list, isSum);
    }
}
