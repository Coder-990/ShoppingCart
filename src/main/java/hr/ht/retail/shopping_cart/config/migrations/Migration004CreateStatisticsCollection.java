package hr.ht.retail.shopping_cart.config.migrations;

import com.mongodb.client.model.IndexOptions;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "create-statistics-collection", order = "004", author = "dBorovcak")
@RequiredArgsConstructor
public class Migration004CreateStatisticsCollection {

    private final MongoTemplate mongoTemplate;

    @Execution
    public void createStatisticsCollection() {
        mongoTemplate.createCollection("statistics")
                .createIndex(new Document("id", 1), new IndexOptions().name("statistics-id-index"));
    }

    @RollbackExecution
    public void rollbackStatisticsCollection() {
        // Change is backward-compatible; no need to implement a rollback mechanism.
    }
}
