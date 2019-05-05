/*
	Modified by: Philip Amwata
	Date: 05/05/2019
*/
package molecule;

public class Carbon extends Thread {

	private static int carbonCounter = 0;
	private int id;
	private Methane sharedMethane;

	public Carbon(Methane methane_obj) {
		Carbon.carbonCounter += 1;
		id = carbonCounter;
		this.sharedMethane = methane_obj;
	}

	public void run() {
		try {
			sharedMethane.mutex.acquire(); // Sync using mutex lock
			sharedMethane.addCarbon(); // Add Carbon to methane molecule
			// Check to see if an entire methane molecule has formed
			if (sharedMethane.getHydrogen() >= 4) {
				sharedMethane.hydrogensQ.release(4); // Release all aquired hydrogen semaphore locks
				sharedMethane.removeHydrogen(4); // Remove all hydrogen atoms from methane molecule
				sharedMethane.carbonQ.release(); // Release all aquired cabron locks
				sharedMethane.removeCarbon(1); // Remove all carbon atoms from methane molecule
				System.out.println("---Group ready for bonding---"); // Log that a methane molecule is to be formed
			} else {
				sharedMethane.mutex.release(); // End mutex sync block
			}
			sharedMethane.carbonQ.acquire(); // Aquire a carbon semaphore lock
			sharedMethane.bond("C" + this.id); // Bond carbon atom to methane molecule
			sharedMethane.barrier.b_wait(); // Invoke reusable barrier Phase1() and Phase2()
			sharedMethane.mutex.release(); // End mutex sync block

		} catch (InterruptedException ex) {
			/* not handling this */}
		// System.out.println(" ");
	}

}
