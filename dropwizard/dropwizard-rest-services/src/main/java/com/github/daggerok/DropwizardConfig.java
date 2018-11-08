package com.github.daggerok;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DropwizardConfig extends Configuration {

  @Valid
  @Getter
  @NotNull
  @JsonbProperty("database")
  private final DataSourceFactory dataSourceFactory = new DataSourceFactory();
}
