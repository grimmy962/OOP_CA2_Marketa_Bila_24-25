package org.example;

import java.util.ArrayList;
import java.util.List;

public class ContainerManager {
    //start with making the internal list
    private List<IMeasurableContainer> containers;

    //an array list
    public ContainerManager() {
        this.containers = new ArrayList<>();
    }

    //add( container )  - to add a container to the list
    public void add(IMeasurableContainer container){
        containers.add(container);
    }

    //totalWeight( )  - to return the total weight of all objects currently in the ContainerManager
    public double totalWeight(){
        double total = 0;
        for(IMeasurableContainer container : containers){
            total += container.weight();
        }
        return total;
    }

    //totalRectangularVolume( ) – to return the total ‘rectangular’ volume of all objects
    public double totalRectangularVolume(){
            double total = 0;
            for(IMeasurableContainer container : containers){
                total += container.rectangularVolume();
            }
            return total;
    }

    //clearAll() – remove all objects
    public void clearAll(){
        containers.clear();
    }

    //getAllContainers() – return a List of all containers
    public List<IMeasurableContainer> getAllContainers(){
        return new ArrayList<>(containers);
    }
}
