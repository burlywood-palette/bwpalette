package com.burlywoodpalette.bwpalette.service.core;

import de.androidpit.colorthief.ColorThief;
import de.androidpit.colorthief.MMCQ.CMap;
import de.androidpit.colorthief.MMCQ.VBox;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultImagePalletProcessor implements ImagePalletProcessor {

  private final ImagePalletMapper imagePalletMapper;

  @Autowired
  public DefaultImagePalletProcessor(ImagePalletMapper imagePalletMapper) {
    this.imagePalletMapper = imagePalletMapper;
  }

  @Override
  public ImagePallet getDominantColor(BufferedImage img) {
    CMap colorMap = ColorThief.getColorMap(img, 5);
    ArrayList<VBox> vboxes = colorMap.vboxes;
    if (vboxes.isEmpty()) {
      throw new IllegalStateException("VBoxes is empty!");
    }
    VBox dominantColor = vboxes.get(0);
    return imagePalletMapper.map(dominantColor);
  }

  @Override
  public List<ImagePallet> getPallet(BufferedImage img, int colorCount) {
    CMap colorMap = ColorThief.getColorMap(img, colorCount);
    return colorMap.vboxes.stream()
        .map(imagePalletMapper::map)
        .collect(Collectors.toList());
  }
}
