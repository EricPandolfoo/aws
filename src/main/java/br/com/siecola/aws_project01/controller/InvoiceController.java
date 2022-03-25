package br.com.siecola.aws_project01.controller;

import br.com.siecola.aws_project01.entity.Invoice;
import br.com.siecola.aws_project01.entity.UrlResponse;
import br.com.siecola.aws_project01.repository.InvoiceRepository;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    public static final Instant FIVE_MINUTES_EXPIRATION = Instant.now().plus(Duration.ofMinutes(5));

    @Value("${aws.s3.bucket.invoice.name}")
    private String bucketName;

    private final AmazonS3 amazonS3;
    private final InvoiceRepository invoiceRepository;

    public InvoiceController(AmazonS3 amazonS3, InvoiceRepository invoiceRepository) {
        this.amazonS3 = amazonS3;
        this.invoiceRepository = invoiceRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UrlResponse createInvoiceUrl() {

        String processId = UUID.randomUUID().toString();

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, processId)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(Date.from(FIVE_MINUTES_EXPIRATION));

        String url = amazonS3.generatePresignedUrl(
                generatePresignedUrlRequest).toString();

        return new UrlResponse(url, FIVE_MINUTES_EXPIRATION.getEpochSecond());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @GetMapping(path = "/customer-name")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> findByCustomerName(@RequestParam
                                                        String customerName) {
        return invoiceRepository.findAllByCustomerName(customerName);
    }
}
