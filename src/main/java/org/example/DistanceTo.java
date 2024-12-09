package org.example;

public class DistanceTo implements Comparable<DistanceTo> {
     String city;
     int distance;

    public DistanceTo(String city, int distance)
    {
        this.city = city;
        this.distance = distance;
    }
    public String getTarget()
    {
        return city;
    }
    public int getDistance()
    {
        return distance;
    }
    @Override
    public int compareTo(DistanceTo other) {
        return Integer.compare(this.distance, other.distance);
    }
}
