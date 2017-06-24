package org.nagarro.microservice.customer.apis;

import java.util.Calendar;

import org.nagarro.microservice.customer.model.CommentCollectionResource;
import org.nagarro.microservice.customer.model.CommentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


/**
 * The {@link HystrixCommand} works since Spring makes a proxy to intercept the
 * request and call the fallback method if the method errs.<br>
 * <br>
 * 
 * Hence the {@link HystrixCommand} needs to be in separate {@link Component} or
 * {@link Service} stereotypes so that Spring can proxy them.
 * 
 * @author gauravgoyal
 *
 */
@Service
public class CommentsService {

	@Autowired
	private OAuth2RestOperations restTemplate;

	/**
	 * Returns the comments for the task; note that this applies a circuit
	 * breaker that would return the default value if the comments-webservice
	 * was down.
	 * 
	 * @param taskId
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFallbackCommentsForTask", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000") })
	public CommentCollectionResource getCommentsForTask(String taskId) {
		// Get the comments for this task
		return restTemplate.getForObject(String.format("http://comments-webservice/comments/%s", taskId),
				CommentCollectionResource.class);

	}

	/**
	 * Gets the default comments for task. Need to add the suppress warning
	 * since the method is not directly used by the class.
	 *
	 * @return the default comments for task
	 */
	@SuppressWarnings("unused")
	private CommentCollectionResource getFallbackCommentsForTask(String taskId) {
		// Get the default comments
		CommentCollectionResource comments = new CommentCollectionResource();
		comments.addComment(new CommentResource(taskId, "default comment", Calendar.getInstance().getTime()));

		return comments;
	}
}
