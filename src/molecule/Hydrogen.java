package molecule;

public class Hydrogen extends Thread {

	private static int carbonCounter = 0;
	private int id;
	private Methane sharedMethane;

	public Hydrogen(Methane methane_obj) {
		Hydrogen.carbonCounter += 1;
		id = carbonCounter;
		this.sharedMethane = methane_obj;

	}

	public void run() {
		try {
			sharedMethane.mutex.acquire();
			sharedMethane.addHydrogen();
			if (sharedMethane.getHydrogen() >= 4 && sharedMethane.getCarbon() >= 1) {
				sharedMethane.hydrogensQ.release(4);
				sharedMethane.removeHydrogen(4);
				sharedMethane.carbonQ.release();
				sharedMethane.removeCarbon(1);
			} else if (sharedMethane.getCarbon() == 0 && sharedMethane.getHydrogen() == 1) {
				System.out.println("---Group ready for bonding---");
				sharedMethane.mutex.release();
			} else {
				sharedMethane.mutex.release();
			}
			sharedMethane.hydrogensQ.acquire();
			sharedMethane.bond("H" + this.id);
			sharedMethane.barrier.b_wait();
		} catch (InterruptedException ex) {
			/* not handling this */}
		// System.out.println(" ");
	}

}
