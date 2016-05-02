/**
 * 
 */
package org.mongodb.morphia.weightdetails;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;

/**
 * @author Sampath
 *
 */
@Entity
public class WeightDetails 
{
    @Id private ObjectId id;

	private String timestamp;
	private String weight;
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
}
