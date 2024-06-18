package hr.ht.retail.shoppingCart.config.migrations;

import com.mongodb.client.model.IndexOptions;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "create-cart_item-collection", order = "002", author = "dBorovcak")
@RequiredArgsConstructor
public class Migration002CreateCartItemCollection {

    private final MongoTemplate mongoTemplate;

    @Execution
    public void createCartItemCollection() {
        mongoTemplate.createCollection("cart_item")
                .createIndex(new Document("id", 1), new IndexOptions().name("cart_item-id-index"));
    }

    @RollbackExecution
    public void rollbackCartItemCollection() {
        // Change is backward-compatible; no need to implement a rollback mechanism.
    }
}
