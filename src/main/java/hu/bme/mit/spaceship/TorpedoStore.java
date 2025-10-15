package hu.bme.mit.spaceship;

import java.util.Random;

public class TorpedoStore {

  // rate of failing to fire torpedos [0.0, 1.0]
  private double FAILURE_RATE = 0.0; //NOSONAR

  //  Reuse a single Random instance
  private static final Random RNG = new Random();

  private int torpedoCount = 0;

  public TorpedoStore(int numberOfTorpedos){
    this.torpedoCount = numberOfTorpedos;
    String failureEnv = System.getenv("IVT_RATE");
    if (failureEnv != null){
      try {
        FAILURE_RATE = Double.parseDouble(failureEnv);
      } catch (NumberFormatException nfe) {
        FAILURE_RATE = 0.0;
      }
    }
  }

  public boolean fire(int numberOfTorpedos){
    if(numberOfTorpedos < 1 || numberOfTorpedos > this.torpedoCount){
      throw new IllegalArgumentException("numberOfTorpedos");
    }


    double r = RNG.nextDouble();

    if (r >= FAILURE_RATE) {
      this.torpedoCount -= numberOfTorpedos;
      return true;
    } else {
      return false;
    }
  }

  public boolean isEmpty(){
    return this.torpedoCount <= 0;
  }

  public int getTorpedoCount() {
    return this.torpedoCount;
  }
}
