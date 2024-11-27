package org.example;

/**
 *  Your Name: Marketa Bila
 *  Class Group: GD2A/SD2A
 */

//TODO: test it in main and add the UML diagram
public class Question1 {    // Interfaces
    public static void main(String[] args) {
        System.out.println("Question 1");

        //instantiate the container manager
        ContainerManager cm = new ContainerManager();

        //instantiate the classes
        Box box1 = new Box(2,3,4,10);
        Cylinder cyl1 = new Cylinder (5,3,8);
        Pyramid pyr1 = new Pyramid (6,3,5);

        //add them to the container
        cm.add(box1);
        cm.add(cyl1);
        cm.add(pyr1);

        //display total weight
        System.out.println("Total Weight: " + cm.totalWeight());

        //display total rectangular volume
        System.out.println("Total Rectangular Volume: " + cm.totalRectangularVolume());

        //get all containers and print details
        System.out.println("All Container Details: ");
        for(IMeasurableContainer container : cm.getAllContainers()){
            if(container instanceof Box){
                Box b = (Box) container;
                System.out.println("Box = Length: " +b.getLength() +
                                       ", Width: " + b.getWidth() +
                                       ", Depth: " + b.getWidth() +
                                       ", Weight: " + b.getWeight());
            }
            else if(container instanceof Cylinder) {
                Cylinder c = (Cylinder) container;
                System.out.println("Cylinder = Height: " + c.getHeight() +
                                            ", Diameter: " + c.getDiameter() +
                                            ", Weight: " + c.getWeight());
            }
            else if(container instanceof Pyramid){
                Pyramid p = (Pyramid) container;
                System.out.println("Pyramid = Length: " + p.getLength() +
                                    ", Side length: " + p.getSideLength() +
                                    ", Weight: " + p.getWeight());
            }
        }

    }
}
