package com.example.aiAgents.nvidiaConnection.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NvidiaResponse {
    private String id;
    private List<Choices> choices;
    private Long created;
    private String model;
    private String service_tier;
    private String system_fingerprint;
    private String object;
    private Usage usage;
    private NvExt nvext;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Usage {

    private Integer prompt_tokens;
    private Integer completion_tokens;
    private Integer total_tokens;

}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class NvExt {

    private SpecDecode spec_decode;
    private SchedulerSnapshot scheduler_snapshot;
    private RequestThroughput request_throughput;

}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class SpecDecode {

    private Boolean enabled;
    private Integer num_speculative_tokens;
    private String method;
    private Integer num_drafts;
    private Integer num_draft_tokens;
    private Integer num_accepted_tokens;
    private Integer num_rejected_tokens;
    private Double acceptance_rate;
    private Double acceptance_length;
    private List<Integer> accepted_tokens_per_position;
    private List<Integer> draft_tokens_per_position;
    private List<Double> acceptance_rate_per_position;

}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class SchedulerSnapshot {

    private Integer num_running_reqs;
    private Integer num_waiting_reqs;

}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class RequestThroughput {

    private Double e2e_latency_seconds;
    private Double generation_tokens_per_second;
    private Double draft_tokens_per_second;

}