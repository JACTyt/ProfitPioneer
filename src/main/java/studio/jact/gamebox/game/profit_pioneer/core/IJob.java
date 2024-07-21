package studio.jact.gamebox.game.profit_pioneer.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Job.class, name = "job"),
        @JsonSubTypes.Type(value = TemporaryJob.class, name = "temporaryJob") // Add other concrete types if necessary
})
public interface IJob {
    void Initialize();

    void assignWorker(IWorker worker);

    public String getName();

    public int getId();

    public int getCapacity();

    public List<IWorker> getAssignedWorkers();

    @JsonIgnore
    public int getDealProfit();
}