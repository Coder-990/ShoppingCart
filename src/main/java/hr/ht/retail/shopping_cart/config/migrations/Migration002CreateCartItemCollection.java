package hr.ht.retail.shopping_cart.config.migrations;

import com.mongodb.client.model.IndexOptions;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "create-cartItem-collection", order = "002", author = "dBorovcak")
@RequiredArgsConstructor
public class Migration002CreateCartItemCollection {

    private final MongoTemplate mongoTemplate;

    @Execution
    public void createCartItemCollection() {
        mongoTemplate.createCollection("cartItem")
                .createIndex(new Document("id", 1), new IndexOptions().name("cartItem-id-index"));
    }

    @RollbackExecution
    public void rollbackCartItemCollection() {
        // Change is backward-compatible; no need to implement a rollback mechanism.
    }
}
