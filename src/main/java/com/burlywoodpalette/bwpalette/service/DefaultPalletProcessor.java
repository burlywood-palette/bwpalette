package com.burlywoodpalette.bwpalette.service;

import com.burlywoodpalette.bwpalette.config.PalletImageProps;
import com.burlywoodpalette.bwpalette.service.core.ImagePallet;
import com.burlywoodpalette.bwpalette.service.core.ImagePalletProcessor;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.List;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class DefaultPalletProcessor implements PalletProcessor {

  private final PalletImageProps palletImageProps;
  private final ImagePalletProcessor imagePalletProcessor;

  @Autowired
  public DefaultPalletProcessor(PalletImageProps palletImageProps,
      ImagePalletProcessor imagePalletProcessor) {
    this.palletImageProps = palletImageProps;
    this.imagePalletProcessor = imagePalletProcessor;
  }

  @Override
  public Mono<ImagePallet> getDominantPallet(InputStream inputStream) {
    return buildBufferImage(inputStream)
        .map(imagePalletProcessor::getDominantColor)
        .doOnError(error -> log.error("Error getting dominant pallet.", error));
  }

  @Override
  public Flux<ImagePallet> getPallet(InputStream inputStream) {
    return buildBufferImage(inputStream)
        .map(this::getPallet)
        .flatMapMany(Flux::fromIterable)
        .doOnError(error -> log.error("Error getting pallets.", error));
  }

  private Mono<BufferedImage> buildBufferImage(InputStream inputStream) {
    return Mono.justOrEmpty(inputStream)
        .flatMap(in -> Mono.fromCallable(() -> ImageIO.read(in)));
  }

  private List<ImagePallet> getPallet(BufferedImage bf) {
    return imagePalletProcessor.getPallet(bf, palletImageProps.getCount());
  }
}
