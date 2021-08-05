package com.burlywoodpalette.bwpalette.service;

import com.burlywoodpalette.bwpalette.dto.ImagePallet;
import java.io.InputStream;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PalletProcessor {

  Mono<ImagePallet> getDominantPallet(InputStream inputStream);

  Flux<ImagePallet> getPallet(InputStream inputStream);

}
