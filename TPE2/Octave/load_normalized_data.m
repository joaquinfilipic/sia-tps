function [patterns, expected_outputs, x1_abs_max, x2_abs_max, z_abs_max] = load_normalized_data(file)
        
    M = dlmread(file)(2:end,:);
    X1X2Z = [M(:,1:end)];

    x1_max = max(X1X2Z(:,1));
	x1_min = min(X1X2Z(:,1));
	if(abs(x1_max) > abs(x1_min))
		x1_abs_max = abs(x1_max);
	else
		x1_abs_max = abs(x1_min);
	end

	x2_max = max(X1X2Z(:,2));
	x2_min = min(X1X2Z(:,2));
	if(abs(x2_max) > abs(x2_min))
		x2_abs_max = abs(x2_max);
	else
		x2_abs_max = abs(x2_min);
	end

	z_max = max(X1X2Z(:,3));
	z_min = min(X1X2Z(:,3));
	if(abs(z_max) > abs(z_min))
		z_abs_max = abs(z_max);
	else
		z_abs_max = abs(z_min);
	end

    X1X2Z(:,1) = X1X2Z(:,1) ./ (x1_abs_max);
    X1X2Z(:,2) = X1X2Z(:,2) ./ (x2_abs_max);
    X1X2Z(:,3) = X1X2Z(:,3) ./ (z_abs_max);

    patterns = X1X2Z(:,1:end-1);
    expected_outputs = X1X2Z(:,end);

end