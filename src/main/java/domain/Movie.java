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
@NamedQueries({
	@NamedQuery (name="Movie.all" , query="Select movie FROM Movie movie "),
	@NamedQuery (name="Movie.id", query="Select m FROM Movie m WHERE m.Id=:id"),
	@NamedQuery (name="Movie.filterName" , query="SELECT m FROM Movie m WHERE m.name=:nameMovie"),
	@NamedQuery (name="Movie.filterCategory" , query="SELECT p FROM Product p WHERE p.productCategory=:category")
})

@Entity
public class Movie {
    private String name;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) 
    private int Id;
    private String description;
    private List<Comment> comments;
    private List<Rating> Ratting = new ArrayList<Rating>();
    private List<Actor> actors = new ArrayList<>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlTransient
	@OneToMany (mappedBy = "Movie")
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	@XmlTransient
	@OneToMany (mappedBy = "Movie")
	public List<Rating> getRatting() {
		return Ratting;
	}
	public void setRatting(List<Rating> ratting) {
		Ratting = ratting;
	}
	@XmlTransient
	@OneToMany (mappedBy = "Movie")
	public List<Actor> getActors() {
		return actors;
	}
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
    
    
}
