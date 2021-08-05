package com.burlywoodpalette.bwpalette.dto;

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
