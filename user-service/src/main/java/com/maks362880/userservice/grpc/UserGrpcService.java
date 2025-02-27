package com.maks362880.userservice.grpc;

import com.maks362880.user_service.proto.UserServiceGrpc;
import com.maks362880.user_service.proto.RegisterRequest;
import com.maks362880.user_service.proto.RegisterResponse;
import com.maks362880.user_service.proto.StatusCode;
import com.maks362880.user_service.proto.LoginResponse;
import com.maks362880.user_service.proto.LoginRequest;
import com.maks362880.user_service.proto.GetUserRequest;
import com.maks362880.user_service.proto.GetUserResponse;
import com.maks362880.userservice.model.User;
import com.maks362880.userservice.service.UserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneOffset;

@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserService userService;

    @Override
    public void register(RegisterRequest request, StreamObserver<RegisterResponse> responseObserver) {
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword());
        newUser.setEmail(request.getEmail());
        newUser.setRole(request.getRole());

        User registeredUser = userService.registerUser(newUser);

        RegisterResponse response = RegisterResponse.newBuilder()
                .setUserId(registeredUser.getId())
                .setUsername(registeredUser.getUsername())
                .setEmail(registeredUser.getEmail())
                .setStatusCode(StatusCode.SUCCESS)
                .setRegisteredAt(com.google.protobuf.Timestamp.newBuilder()
                        .setSeconds(registeredUser.getRegisteredAt().toEpochSecond(ZoneOffset.UTC))
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        User requestUser = userService.loginUser(request.getUsername(), request.getPassword());

        LoginResponse response = LoginResponse.newBuilder()
                .setUserId(requestUser.getId())
                .setToken("generated-jwt-token") // Здесь можно добавить генерацию JWT токена
                .setUsername(requestUser.getUsername())
                .setStatusCode(StatusCode.SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(GetUserRequest request, StreamObserver<GetUserResponse> responseObserver) {
        User requestUser = userService.getUserById(request.getUserId());

        GetUserResponse response = GetUserResponse.newBuilder()
                .setUserId(requestUser.getId())
                .setUsername(requestUser.getUsername())
                .setEmail(requestUser.getEmail())
                .setRole(requestUser.getRole())
                .setStatusCode(StatusCode.SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}