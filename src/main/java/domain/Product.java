package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@NamedQueries({
	@NamedQuery (name="Product.all" , query="Select p FROM Product p "),
	@NamedQuery (name="Product.id", query="Select p FROM Product p WHERE p.productId=:id"),
	@NamedQuery (name="Product.filterPrize", query="SELECT p FROM Product p WHERE p.prize BETWEEN :toPrize AND :fromPrize"),
	@NamedQuery (name="Product.filterName" , query="SELECT p FROM Product p WHERE p.name=:nameProduct"),
	@NamedQuery (name="Product.filterCategory" , query="SELECT p FROM Product p WHERE p.productCategory=:category")
})

public class Product {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int productId;
	private String description;
	private String name;
	private double prize;
	private String productCategory;
	private List<Comment> comments = new ArrayList<Comment>();
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrize() {
		return prize;
	}
	public void setPrize(double prize) {
		this.prize = prize;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	@XmlTransient
	@OneToMany (mappedBy="product")
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	
}
