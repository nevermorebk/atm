package com.homdirect.batch;

import com.homdirect.batch.entity.Account;
import com.homdirect.batch.entity.Transaction;
import com.homdirect.batch.util.CsvUtil;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
public class BatchApplication {

    @Scheduled(cron = "0 47 11 ? * MON-FRI")
    public void exportAccountCsv() throws Exception {
        String fileCsv = System.getProperty("user.dir")+ "/src/main/resources/Account.csv";
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Account>> responseEntity = template.exchange("http://localhost:/accounts/1", HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
        });
        List<Account> accounts = responseEntity.getBody();
        FileWriter writeFile = new FileWriter(fileCsv);
        CsvUtil.writerLine(writeFile, Arrays.asList("id", "accountNumber", "username", "amount"));
        for (Account account : accounts) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(account.getId()));
            list.add(account.getAccountNumber());
            list.add(account.getUsername());
            list.add(String.valueOf(account.getAmount()));
            CsvUtil.writerLine(writeFile, list);
        }
        writeFile.flush();
        writeFile.close();
    }

    @Scheduled(cron = "0 47 11 ? * MON-FRI")
    public void exportTransactionCsv() throws Exception {
        String fileCsv = System.getProperty("user.dir") + "/src/main/resources/Transaction.csv";
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Transaction>> responseEntity = template.exchange("http://localhost:/transactions/1", HttpMethod.GET, null, new ParameterizedTypeReference<List<Transaction>>() {
        });
        List<Transaction> transactions = responseEntity.getBody();
        FileWriter writeFile = new FileWriter(fileCsv);
        CsvUtil.writerLine(writeFile, Arrays.asList("Id", "FromAccount", "ToAccount", "Type", "Content", "Status",
                "TransferAmount", "DateTime"));
        for (Transaction transaction : transactions) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(transaction.getId()));
            list.add(transaction.getFromAccount());
            list.add(chooseToAccount(transaction.getToAccount()));
            list.add(String.valueOf(transaction.getType()));
            list.add(transaction.getContent());
            list.add(transaction.getStatus());
            list.add(String.valueOf(transaction.getAmount()));
            list.add(String.valueOf(transaction.getTime()));
            CsvUtil.writerLine(writeFile, list);
        }
        writeFile.flush();
        writeFile.close();
    }

    private String chooseToAccount(String toAccount) {
        if (toAccount == null) {
            return " ";
        }
        return toAccount;
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }
}
