classdef Output
	properties

        %obtained output potentials while training
        network_outputs;

        train_data_accuracy;

        %last run weights
        w_network;

        %epochs run
        epoch_count_index = 1;

        %booleans
        saturation;
        cut_condition;

        eta_record;

        %last run errors
        errors;
        ecm;
        ecm_record;

        %tests
        test_network_outputs;
        test_data_accuracy;
        test_errors;
        test_ecm;
	
	end

	methods
	
	   function this = Output(net)
        this.w_network = net;
       end

	end
end