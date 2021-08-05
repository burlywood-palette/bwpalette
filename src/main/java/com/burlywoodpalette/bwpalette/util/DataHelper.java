package com.burlywoodpalette.bwpalette.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class DataHelper {

  public static InputStream getInputStreamFromFluxDataBuffer(Flux<DataBuffer> data) throws IOException {
    PipedOutputStream osPipe = new PipedOutputStream();
    PipedInputStream isPipe = new PipedInputStream(osPipe);

    DataBufferUtils.write(data, osPipe)
        .subscribeOn(Schedulers.boundedElastic())
        .doOnComplete(() -> {
          try {
            osPipe.close();
          } catch (IOException ignored) {
            log.error("doOnComplete", ignored);
          }
        })
        .doFinally(it -> {
          try {
            osPipe.close();
          } catch (IOException ignored) {
            log.error("doFinally", ignored);
          }
        })
        .subscribe(DataBufferUtils.releaseConsumer());
    return isPipe;
  }

  public static Mono<InputStream> getISFromFluxDataBuffer(Flux<DataBuffer> data) {
    return Mono.fromCallable(() -> getInputStreamFromFluxDataBuffer(data));
  }

}
