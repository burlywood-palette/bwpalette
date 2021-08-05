package com.burlywoodpalette.bwpalette.service.core;

import com.burlywoodpalette.bwpalette.dto.ImagePallet;
import de.androidpit.colorthief.MMCQ.VBox;
import org.springframework.stereotype.Component;

@Component
public class ImagePalletMapper {

  public ImagePallet map(VBox vBox) {
    int[] rgb = vBox.avg(false);
    String rgbHexString = createRGBHexString(rgb);
    return ImagePallet.builder()
        .rgb(rgb)
        .rgbHex(rgbHexString)
        .build();
  }

  private String createRGBHexString(int[] rgb) {
    String rgbHex = Integer.toHexString(rgb[0] << 16 | rgb[1] << 8 | rgb[2]);

    // Left-pad with 0s
    int length = rgbHex.length();
    if (length < 6) {
      rgbHex = "00000".substring(0, 6 - length) + rgbHex;
    }

    return "#" + rgbHex;
  }

}
