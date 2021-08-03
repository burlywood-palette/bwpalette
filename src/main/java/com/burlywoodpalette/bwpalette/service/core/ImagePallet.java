package com.burlywoodpalette.bwpalette.service.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImagePallet {

  private int[] rgb;
  private String rgbHex;

}
