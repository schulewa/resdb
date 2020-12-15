package com.apschulewitz.resdb.common.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import javax.persistence.Embeddable;
import javax.servlet.http.PushBuilder;

@Data
@Builder
@Embeddable
public class Latitude {

	private String value;

  public static boolean isValid(String latitude) {
    return true; // TODO implement real validation method for latitude
  }

	@Tolerate
	public Latitude() {

  }


}
