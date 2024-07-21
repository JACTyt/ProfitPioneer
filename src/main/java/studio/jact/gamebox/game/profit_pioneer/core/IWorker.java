package studio.jact.gamebox.game.profit_pioneer.core;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Worker.class, name = "worker")
})
public interface IWorker {
    public void doJob(IJob job);

    public String getFullName();

    public int getId();

    public int getPayment();
}
