package com.burlywoodpalette.bwpalette.service.core;

import com.burlywoodpalette.bwpalette.dto.ImagePallet;
import java.awt.image.BufferedImage;
import java.util.List;

public interface ImagePalletProcessor {

  ImagePallet getDominantColor(BufferedImage img);

  List<ImagePallet> getPallet(BufferedImage img, int colorCount);
}
