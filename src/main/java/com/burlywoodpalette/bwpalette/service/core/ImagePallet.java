package com.burlywoodpalette.bwpalette.service.core;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImagePallet {

  int[] rgb;
  String rgbHex;

}
