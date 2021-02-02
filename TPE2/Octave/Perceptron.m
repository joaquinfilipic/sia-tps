classdef Perceptron
	properties

		%layers: counting hidden layers and the output layer
		layers;
    
		%neurons_per_layer: counting the input layer, hidden layers and the output layer
		neurons_per_layer;

		%activation function and its derivative
		activation;
		activation_d;
		
		saturation_value;
		cut_condition_value;
		epsilon;

		momentum_enabled; 
		alpha;

		eta;
		adaptative_eta_enabled;
		eta_check_steps;
		eta_increment;
		eta_decrement;

		epochs;
		percentage;
    
    data_file;
	
	end

	methods
	
	function this = Perceptron(config)
    
    %configuration file
    CONFIG_FILE = strcat('./configuration/config_', config);
    CONFIG_FILE = strcat(CONFIG_FILE,'.txt');
    
    %Activation functions and their derivatives
    tanh = @tanh;
	tanh_d = @(x)(1 - x.^2);
	exp = @(x)(1./(1 + exp(-x)));
	exp_d = @(x)(x.*(1-x));
    
    %Get all parameters from the file
    file = fopen(CONFIG_FILE);
    parameters = textscan(file,'%s %s');
    fclose(file);
    
    this.layers = scan_parameter(parameters, 'layers');
    this.neurons_per_layer = scan_parameter(parameters, 'neurons_per_layer');
    
    activation_function = scan_parameter(parameters, 'activation_function');
    if (strcmp(activation_function, 'tanh'))
      this.activation = tanh;
      this.activation_d = tanh_d;
    else
      this.activation = exp;
      this.activation_d = exp_d;
    endif
    
    this.saturation_value = scan_parameter(parameters, 'saturation_value');
    this.cut_condition_value = scan_parameter(parameters, 'cut_condition_value');
    this.epsilon = scan_parameter(parameters, 'epsilon');
    this.momentum_enabled = scan_parameter(parameters, 'momentum_enabled');
    this.alpha = scan_parameter(parameters, 'alpha');
    this.eta = scan_parameter(parameters, 'eta');
    this.adaptative_eta_enabled = scan_parameter(parameters, 'adaptative_eta_enabled');
    this.eta_check_steps = scan_parameter(parameters, 'eta_check_steps');
    this.eta_increment = scan_parameter(parameters, 'eta_increment');
    this.eta_decrement = scan_parameter(parameters, 'eta_decrement');
    this.epochs = scan_parameter(parameters, 'epochs');
    this.percentage = scan_parameter(parameters, 'percentage');
    this.data_file = scan_parameter(parameters, 'data_file');
    
	end

	end
end