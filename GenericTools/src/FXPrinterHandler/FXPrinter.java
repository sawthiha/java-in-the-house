/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXPrinterHandler;

import java.util.Iterator;
import javafx.print.*;
import javafx.scene.*;
import javafx.scene.transform.Scale;
import javafx.geometry.Bounds;

public class FXPrinter  {
	/**
	 * Constructs a default FX printer object with default page layout.
	 */
	public FXPrinter() throws SecurityException, NullPointerException  {
		this.printer = Printer.getDefaultPrinter();
		this.page = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.EQUAL);
		setup();
	}

	/**
	 * Sets the scale to printable one.
	 *
	 * @param node to be transformed
	 */
	public void setScale(Node node)  {
		double x, y;
		Bounds bounds = node.getBoundsInParent();
		x = printableX / bounds.getWidth();
                y = printableY / bounds.getHeight();
                node.getTransforms().add(new Scale(x, y));
	}

	/**
	 * Gets a specific printer object.
	 *
	 * @param printer name
	 *
	 * @return printer object or null
	 */
	public Printer getSpecificPrinter(String printer)  {
		Printer tmp;
		Iterator<Printer> i = Printer.getAllPrinters().iterator();
		
		while(i.hasNext())  {
			tmp = i.next();
			if(tmp.getName().compareTo(printer) == 0)  {
				return tmp;
			}
		}

		return null;
	}

	/**
	 * Print the node.
	 *
	 * @param node to be printed
	 * @return true if printer job is added to the spool,or false otherwise
	 */
	public boolean print(Node node)  {
            PrinterJob job = PrinterJob.createPrinterJob(this.printer);
            boolean success;
            this.setScale(node);
            success = job.printPage(page, node);
            if(success)  {
                return job.endJob();
            }
            return success;
	}

	/**
	 * Set up
	 */
	private void setup()  {
		this.printableX = page.getPrintableWidth();
		this.printableY = page.getPrintableHeight();
	}

	/**
	 * Printer Object
	 */
	private final Printer printer;
	/**
	 * Page Layout
	 */
	private final PageLayout page;
	/**
	 * Paper Types
	 * 0 - A0
	 * 1 - A1
	 * 2 - A2
	 * 3 - A3
	 * 4 - A4
	 * 5 - A5
	 * 6 - A6
	 */
	
	/**
	 * Printable X
	 */
	private double printableX;
	/**
	 * Printable Y
	 */
	private double printableY;
}