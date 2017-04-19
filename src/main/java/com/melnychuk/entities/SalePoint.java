package com.melnychuk.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sale_points", schema = "heroku_b196e4189240738")
public class SalePoint
{
    private int id;
    private String name;
    private String city;
    private String street;
    private String building;
    private double lng;
    private double lat;
    private double distance;
    private Set<Join> joins = new HashSet<Join>();



//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "sp_to_beer",
//            joinColumns = { @JoinColumn(name = "point_id", nullable = false) },
//            inverseJoinColumns = { @JoinColumn(name = "beer_id", nullable = false)})
//    private Set<Beer> beers = new HashSet<Beer>(0);

    @Id
    @Column(name = "id", nullable = false)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 16)
    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @Basic
    @Column(name = "street", nullable = false, length = 32)
    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    @Basic
    @Column(name = "building", nullable = false, length = 16)
    public String getBuilding()
    {
        return building;
    }

    public void setBuilding(String building)
    {
        this.building = building;
    }

    @Basic
    @Column(name = "lng", nullable = false, precision = 0)
    public double getLng()
    {
        return lng;
    }

    public void setLng(double lng)
    {
        this.lng = lng;
    }

    @Basic
    @Column(name = "lat", nullable = false, precision = 0)
    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    @Transient
    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "salePoint", targetEntity = Join.class,orphanRemoval = true, cascade = CascadeType.ALL)
    public Set<Join> getJoins()
    {
        return joins;
    }

    public void setJoins(Set<Join> joins)
    {
        this.joins = joins;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalePoint point = (SalePoint) o;

        if (id != point.id) return false;
        if (Double.compare(point.lng, lng) != 0) return false;
        if (Double.compare(point.lat, lat) != 0) return false;
        if (name != null ? !name.equals(point.name) : point.name != null) return false;
        if (city != null ? !city.equals(point.city) : point.city != null) return false;
        if (street != null ? !street.equals(point.street) : point.street != null) return false;
        if (building != null ? !building.equals(point.building) : point.building != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (building != null ? building.hashCode() : 0);
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString()
    {
        String s = String.format("%s %s %s", city, street, building);
        System.out.println(s);

        return s;
    }

    public SalePoint()
    {
    }

    public SalePoint(String name, double lat, double lng)
    {
        this.name = name;
        this.lng = lng;
        this.lat = lat;
    }
}
