package hr.ht.retail.shoppingCart.config.migrations;

import com.mongodb.client.model.IndexOptions;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "create-price-collection", order = "001")
public class Migration001CreatePriceCollection {

    @Execution
    public void createPriceCollection(MongoTemplate mongoTemplate) {
        mongoTemplate.createCollection("price")
                .createIndex(new Document("id", 1), new IndexOptions().name("price-id-index"));
    }

    @RollbackExecution
    public void rollbackPriceCollection(MongoTemplate mongoTemplate) {
        //nothing to do here
    }
}
