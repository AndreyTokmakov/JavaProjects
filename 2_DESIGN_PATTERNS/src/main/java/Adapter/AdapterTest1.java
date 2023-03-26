package Adapter;


// Birds implement Bird interface that allows 
// them to fly and make sounds adaptee interface 
interface Bird  {
    public void fly(); 
    public void makeSound(); 
} 

interface ToyDuck { 
    public void squeak(); 
} 
 
// Sparrow
class Sparrow implements Bird { 
    public void fly() { 
        System.out.println("Flying"); 
    }
    
    public void makeSound() { 
        System.out.println("Chirp Chirp"); 
    } 
} 
  
  
class PlasticToyDuck implements ToyDuck { 
    public void squeak() { 
        System.out.println("Squeak"); 
    } 
} 
  
class BirdAdapter implements ToyDuck  { 
    protected Bird bird; 
    
    public BirdAdapter(Bird bird) { 
        this.bird = bird; 
    } 
  
    public void squeak() { 
        bird.makeSound(); 
    } 
} 
  

public class AdapterTest1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bird sparrow = new Sparrow(); 
        ToyDuck toyDuck = new PlasticToyDuck(); 
  
        // Wrap a bird in a birdAdapter so that it behaves like toy duck 
        ToyDuck birdAdapter = new BirdAdapter(sparrow); 
  
        System.out.println("Sparrow..."); 
        sparrow.fly(); 
        sparrow.makeSound(); 
  
        System.out.println("ToyDuck..."); 
        toyDuck.squeak(); 
  
        // toy duck behaving like a bird  
        System.out.println("BirdAdapter..."); 
        birdAdapter.squeak(); 
	}
}
