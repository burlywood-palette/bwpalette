package com.burlywoodpalette.bwpalette.service;

import com.burlywoodpalette.bwpalette.service.core.ImagePallet;
import java.io.InputStream;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PalletProcessor {

  Mono<ImagePallet> getDominantPallet(InputStream inputStream);

  Flux<ImagePallet> getPallet(InputStream inputStream);

}
