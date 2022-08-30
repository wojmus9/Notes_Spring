/**
 * 
 */
package com.example.MyProject.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Wojtek
 *
 */
@Entity
public class Project {
	@Id 	
	@GeneratedValue(strategy = GenerationType.AUTO)
		
	private long id;
	
	private String title;
		
	private String content;
	
	private Date updated;

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(String title, String content, Date updated) {
		super();
		this.title = title;
		this.content = content;
		this.updated = updated;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	
	
	
	
}
