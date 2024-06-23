package hr.ht.retail.shopping_cart.config.migrations;

import com.mongodb.client.model.IndexOptions;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "create-customer-collection", order = "005", author = "dBorovcak")
@RequiredArgsConstructor
public class Migration005CreateCustomerCollection {

    private final MongoTemplate mongoTemplate;

    @Execution
    public void createCustomerCollection() {
        mongoTemplate.createCollection("customer")
                .createIndex(new Document("id", 1), new IndexOptions().name("customer-id-index"));
    }

    @RollbackExecution
    public void rollbackCustomerCollection() {
        // Change is backward-compatible; no need to implement a rollback mechanism.
    }
}
