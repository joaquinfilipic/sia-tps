function plot_expected_output(patterns, expected_outputs, x1_abs_max, x2_abs_max, z_abs_max)
	
	%plot3(patterns(:,1), patterns(:,2), expected_outputs', '.', 'color','red', "markersize", 12);
	%set(gca, "linewidth", 3, "fontsize", 12);
	%legend ("Expected terrain");
	
	x1 = patterns(:,1);
	x2 = patterns(:,2);
	z = expected_outputs;
	ti = -2:.025:2;
	[X1,Y1] = meshgrid(ti,ti);
	Z1 = griddata(x1 .* x1_abs_max,x2 .* x2_abs_max,z .* z_abs_max,X1,Y1);
	mesh(X1,Y1,Z1);
	title ("Expected Terrain");
end