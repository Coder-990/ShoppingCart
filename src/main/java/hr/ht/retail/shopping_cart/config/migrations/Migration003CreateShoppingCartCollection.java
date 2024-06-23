package hr.ht.retail.shopping_cart.config.migrations;

import com.mongodb.client.model.IndexOptions;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "create-shoppingCart-collection", order = "003", author = "dBorovcak")
@RequiredArgsConstructor
public class Migration003CreateShoppingCartCollection {

    private final MongoTemplate mongoTemplate;

    @Execution
    public void createShoppingCartCollection() {
        mongoTemplate.createCollection("shoppingCart")
                .createIndex(new Document("id", 1), new IndexOptions().name("shoppingCart-id-index"));
    }

    @RollbackExecution
    public void rollbackShoppingCartCollection() {
        // Change is backward-compatible; no need to implement a rollback mechanism.
    }
}
