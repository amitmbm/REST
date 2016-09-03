package com.ami.entity;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.Date;

/**
 * ProductCategory
 */

@Entity
@Table(name = "product")
public class Product implements java.io.Serializable {

    private static final long serialVersionUID = -2107661175822965352L;
    private String itemId;
    private String itemName;
    private String itemDesc;
    private String createdBy;
    private String updatedBy;
    private Date createdAt;
    private Date updatedAt;

    public Product() {
    }

    public Product(String itemId, String itemName, String itemDesc) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String catGuid) {
        this.itemId = catGuid;
    }

    @Column(name = "name", unique = true, nullable = false)
    @XmlAttribute(name="name")
    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String catName) {
        this.itemName = catName;
    }

    @Column(name = "description", nullable = false)
    @XmlAttribute(name="description")
    public String getItemDesc() {
        return this.itemDesc;
    }

    public void setItemDesc(String catDesc) {
        this.itemDesc = catDesc;
    }

    @Column(name = "created_by")
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "updated_by")
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "created_at")
    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


}
