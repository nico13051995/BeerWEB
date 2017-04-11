package com.melnychuk.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "sp_to_beer", schema = "beermap", catalog = "")
public class Join
{
    private int id;
    private int pointId;
    private int beerId;
    private Byte g33;
    private Byte g05;
    private Byte p1;
    private Byte p15;
    private Byte p2;
    private Byte k30;
    private Byte k50;

    @JsonIgnore
    private SalePoint salePoint;

    @Id
    @Column(name = "id")
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "point_id", nullable = false, updatable = false, insertable = false)
    public int getPointId()
    {
        return pointId;
    }

    public void setPointId(int pointId)
    {
        this.pointId = pointId;
    }

    @Basic
    @Column(name = "beer_id", nullable = true)
    public int getBeerId()
    {
        return beerId;
    }

    public void setBeerId(int beerId)
    {
        this.beerId = beerId;
    }

    @Basic
    @Column(name = "g33", nullable = true)
    public Byte getG33()
    {
        return g33;
    }

    public void setG33(Byte g33)
    {
        this.g33 = g33;
    }

    @Basic
    @Column(name = "g05", nullable = true)
    public Byte getG05()
    {
        return g05;
    }

    public void setG05(Byte g05)
    {
        this.g05 = g05;
    }

    @Basic
    @Column(name = "p1", nullable = true)
    public Byte getP1()
    {
        return p1;
    }

    public void setP1(Byte p1)
    {
        this.p1 = p1;
    }

    @Basic
    @Column(name = "p1_5", nullable = true)
    public Byte getP15()
    {
        return p15;
    }

    public void setP15(Byte p15)
    {
        this.p15 = p15;
    }

    @Basic
    @Column(name = "p2", nullable = true)
    public Byte getP2()
    {
        return p2;
    }

    public void setP2(Byte p2)
    {
        this.p2 = p2;
    }

    @Basic
    @Column(name = "k30", nullable = true)
    public Byte getK30()
    {
        return k30;
    }

    public void setK30(Byte k30)
    {
        this.k30 = k30;
    }

    @Basic
    @Column(name = "k50", nullable = true)
    public Byte getK50()
    {
        return k50;
    }

    public void setK50(Byte k50)
    {
        this.k50 = k50;
    }

    @ManyToOne
    @JoinColumn(name = "point_id", referencedColumnName = "id")
    public SalePoint getSalePoint()
    {
        return salePoint;
    }

    public void setSalePoint(SalePoint salePoint)
    {
        this.salePoint = salePoint;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Join join = (Join) o;

        if (pointId != join.pointId) return false;
        if (beerId != join.beerId) return false;
        if (g33 != null ? !g33.equals(join.g33) : join.g33 != null) return false;
        if (g05 != null ? !g05.equals(join.g05) : join.g05 != null) return false;
        if (p1 != null ? !p1.equals(join.p1) : join.p1 != null) return false;
        if (p15 != null ? !p15.equals(join.p15) : join.p15 != null) return false;
        if (p2 != null ? !p2.equals(join.p2) : join.p2 != null) return false;
        if (k30 != null ? !k30.equals(join.k30) : join.k30 != null) return false;
        if (k50 != null ? !k50.equals(join.k50) : join.k50 != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = pointId;
        //result = 31 * result + (beerId != null ? beerId.hashCode() : 0);
        result = 31 * result + (g33 != null ? g33.hashCode() : 0);
        result = 31 * result + (g05 != null ? g05.hashCode() : 0);
        result = 31 * result + (p1 != null ? p1.hashCode() : 0);
        result = 31 * result + (p15 != null ? p15.hashCode() : 0);
        result = 31 * result + (p2 != null ? p2.hashCode() : 0);
        result = 31 * result + (k30 != null ? k30.hashCode() : 0);
        result = 31 * result + (k50 != null ? k50.hashCode() : 0);
        return result;
    }
}
