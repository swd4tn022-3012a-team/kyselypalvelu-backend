package com.example.kyselypalvelu.domain;


import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="options")
public class Options {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnore
	private Long optionsId;
	
	@ElementCollection
	@CollectionTable(name ="option_values", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "value")
	private List<String> values;
	public Long getOptionsId() {
		return optionsId;
	}

	public void setOptionsId(Long optionsId) {
		this.optionsId = optionsId;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public Options() {
		super();
	}

	public Options(List<String> values) {
		super();
		this.values = values;
	}
	
}
