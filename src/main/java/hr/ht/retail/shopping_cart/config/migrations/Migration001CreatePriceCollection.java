package hr.ht.retail.shopping_cart.config.migrations;

import com.mongodb.client.model.IndexOptions;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "create-price-collection", order = "001", author = "dBorovcak")
@RequiredArgsConstructor
public class Migration001CreatePriceCollection {

    private final MongoTemplate mongoTemplate;

    @Execution
    public void createPriceCollection() {
        mongoTemplate.createCollection("price")
                .createIndex(new Document("id", 1), new IndexOptions().name("price-id-index"));
    }

    @RollbackExecution
    public void rollbackPriceCollection() {
        // Change is backward-compatible; no need to implement a rollback mechanism.
    }
}
