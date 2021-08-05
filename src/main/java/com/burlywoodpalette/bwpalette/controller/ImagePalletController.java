package com.burlywoodpalette.bwpalette.controller;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import com.burlywoodpalette.bwpalette.dto.ImagePallet;
import com.burlywoodpalette.bwpalette.service.PalletProcessor;
import com.burlywoodpalette.bwpalette.util.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/image-pallet")
public class ImagePalletController {

  private final PalletProcessor palletProcessor;

  @Autowired
  public ImagePalletController(PalletProcessor palletProcessor) {
    this.palletProcessor = palletProcessor;
  }

  @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
  public Flux<ImagePallet> getPallet(@RequestPart Mono<FilePart> image) {
    return image.map(Part::content)
        .flatMap(DataHelper::getISFromFluxDataBuffer)
        .flatMapMany(palletProcessor::getPallet);
  }
}
