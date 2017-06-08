package rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Actor;
import domain.Comment;
import domain.Movie;
import domain.Product;
import domain.Rating;

@Path ("/movie")
@Stateless
public class MovieResources {

	@PersistenceContext
	EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> getAll (){
	return em.createNamedQuery("Movie.all", Movie.class).getResultList();
	}
	
	@POST
	@Path ("add/base")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTestBase(List<Movie> movieList){
		movieList.forEach((movie)->em.persist(movie));
		return Response.ok("ADDED").build();
	}
	
	@GET
	@Path("/get/movie/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovieOnId(@PathParam("id") int id){
		Movie result = em.createNamedQuery("Movie.id",Movie.class).setParameter("id", id).getSingleResult();
		if (result == null ){
			return Response.status(400).build();
			
		}
		return Response.ok(result).build();
	}
	

	@PUT
	@Path("/update/movie/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Movie movie){
		Movie result = em.createNamedQuery("Movie.id", Movie.class).setParameter("id", id).getSingleResult();
		int resultId = result.getId();
		if (result == null ){
			return Response.status(400).build();
		}
		result = movie;
		result.setId(movie.getId());
		em.persist(result);
		return Response.ok(result).build();
	}
		
	@GET
	@Path("filter/name/movie/{nameMovie}")
	@Produces (MediaType.APPLICATION_JSON)
	public Response getProductOnName (@PathParam("nameMovie") String nameMovie){
		Movie result = em.createNamedQuery("Movie.filterName" , Movie.class).setParameter("nameMovie", nameMovie).getSingleResult();
		if (result == null){
			Response.ok("Empty Query").build();
		}
		return Response.ok(result).build();
		
	}
		
	@POST
	@Path("add/comment/{movieId}")
	@Consumes (MediaType.APPLICATION_JSON)
	public Response addCar(@PathParam("movieId") int movieId , Comment comment){
	  Movie result = em.createNamedQuery("Movie.id" , Movie.class)
			  .setParameter("id", movieId)
			  .getSingleResult();
	  if (result == null){
		  return Response.status(400).build();
	  }
	  result.getComments().add(comment);
	  comment.setMovie(result);
	  em.persist(comment);
	  return Response.ok(result).build();
	  
	}
	
	@POST
	@Path("add/rating/{movieId}")
	@Consumes (MediaType.APPLICATION_JSON)
	public Response addRate (@PathParam("movieId") int movieId , Rating rating){
	  Movie result = em.createNamedQuery("Movie.id" , Movie.class)
			  .setParameter("id", movieId)
			  .getSingleResult();
	  if (result == null){
		  return Response.status(400).build();
	  }
	  result.getRatting().add(rating);
	  rating.setMovie(result);
	  em.persist(rating);
	  return Response.ok(result).build();
	  
	}
	
	@POST
	@Path("add/actor/{movieId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addActor (@PathParam("movieId") int movieId, Actor actor){
		 Movie result = em.createNamedQuery("Movie.id" , Movie.class)
				  .setParameter("id", movieId)
				  .getSingleResult();
	if (result == null){
		return Response.ok(400).build();
	}
	result.getActors().add(actor);
	actor.setMovie(result);
	em.persist(actor);
	return Response.ok("ADDED").build();
	
	}
	
	@GET
	@Path("get/actor/on/{movieId}")
	@Produces (MediaType.APPLICATION_JSON)
	public List<Actor> getActors (@PathParam("movieId") int movieId){
		 Movie result = em.createNamedQuery("Movie.id" , Movie.class)
				  .setParameter("id", movieId)
				  .getSingleResult();
	if (result == null){
		return null;
	}
	return result.getActors();
	}
	
	
	@GET
	@Path("get/rating/on/{movieId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRateOnMovie (@PathParam("movieId") int movieId){
		Movie result = em.createNamedQuery("Movie.id" , Movie.class)
				  .setParameter("id", movieId)
				  .getSingleResult();
	if (result == null){
		return Response.status(400).build();
	}
	List<Rating> ratingList = result.getRatting();
	int sum = 0;
	for (Rating rating : ratingList){
		sum =+ rating.getRank();
	}
	Integer score = sum/ratingList.size();
	return Response.ok(score.toString()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setMovie (Movie movie){
		em.persist(movie);
		return Response.ok(movie).build();
	}
	
	@PUT
	@Path("remove/comment/{commentId}/on/{movieId}")
	@Consumes (MediaType.APPLICATION_JSON)
	public Response removeComment (@PathParam("commentId") int commentId, @PathParam("movieId") int movieId){
	Movie result= em.createNamedQuery("Movie.id" , Movie.class).setParameter("id", movieId).getSingleResult();
	if (result == null){
		return Response.status(400).build();
	}
	result.getComments().remove(commentId);
	em.persist(result);
	return Response.ok("Deleted").build();
	}
	
	@GET
	@Path("get/comment/on/{movieId}")
	@Produces (MediaType.APPLICATION_JSON)
	public List<Comment> getListComment (@PathParam("movieId") int movieId){
	Movie result = em.createNamedQuery("Movie.id" , Movie.class).setParameter("id", movieId).getSingleResult();	
	if (result == null){
		return null;
	}
	return result.getComments();
	}
	
	
	
	


	
	
}
