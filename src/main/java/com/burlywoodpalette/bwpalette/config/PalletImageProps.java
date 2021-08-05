package com.burlywoodpalette.bwpalette.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "color-pallet")
public class PalletImageProps {

  private int count;

}
