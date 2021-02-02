function value = scan_parameter (parameters, name)

  # Character used to separate neurons in a string
  NEURONS_SEPARATOR = ',';
  
  # Switch cases that need special treatment
  NEURONS_CASE        = 'neurons_per_layer';
  DATA_FILE_CASE     = 'data_file';
  ACTIVATION_FUNCTION = 'activation_function';
  
  # Separate the pairs name-value into two column cell arrays [N, 1]
  names = parameters{1};
  values = parameters{2};

  # Iterate the parameters
  for i = 1:size(names)(1)
    
    if (strcmp(names(i), name))
      
      switch (name)

        case NEURONS_CASE

          # Neurons separated by NEURONS_SEPARATOR character
          neurons = values{i};

          # Row cell array of neurons [1, N]
          neuron_list = strsplit(neurons, NEURONS_SEPARATOR);
          
          # Initialize neuron vector
          neuron_vector = zeros(1, size(neuron_list)(2));
          
          for j=1:size(neuron_list)(2)
            neuron_vector(j) = str2num(neuron_list{j});
          end

          value = neuron_vector;

        # string parameters case
        case { DATA_FILE_CASE, ACTIVATION_FUNCTION }
          
          value = values{i};
        
        # number parameters case
        otherwise

          value = str2num(values{i});
      
      endswitch

      return;

    end

  end
    
endfunction
