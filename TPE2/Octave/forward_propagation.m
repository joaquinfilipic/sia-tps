function [h,V] = forward_propagation(input_vector, layers, w_network, activation)
	h = {};
	V = {};
	last_layer = input_vector;
	for i = 1:layers
		h{i} = w_network{i} * last_layer;
		V{i} = activation(h{i});
		if i < layers
			%add bias
			V{i} = [-1 ; V{i}];
		end
		last_layer = V{i};
	end
end
