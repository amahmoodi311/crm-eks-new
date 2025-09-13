import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { toast } from "react-toastify";
import { authLogin } from "../../features/auth/authSlice";
import logo from "../../assets/skillcapital.png";
import curved from "../../assets/pinkcrm.png";

const Login = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { status, error } = useSelector((state) => state.auth);

  const [loginData, setLoginData] = useState({
    username: "",
    password: "",
  });

  const [validationErrors, setValidationErrors] = useState({});

  // Check if already logged in
  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      navigate("/leads");
    }
  }, [navigate]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLoginData((prev) => ({
      ...prev,
      [name]: value,
    }));
    // Clear validation error when user starts typing
    if (validationErrors[name]) {
      setValidationErrors((prev) => ({
        ...prev,
        [name]: "",
      }));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent form submission

    // Validate inputs
    const errors = {};
    if (!loginData.username) errors.username = "Username is required";
    if (!loginData.password) errors.password = "Password is required";

    if (Object.keys(errors).length > 0) {
      setValidationErrors(errors);
      return;
    }

    try {
      const result = await dispatch(authLogin(loginData)).unwrap();
      if (result) {
        toast.success("Successfully logged in!");
        navigate("/leads");
      }
    } catch (err) {
      toast.error(err || "Invalid username or password");
    }
  };

  return (
    <div className="flex h-screen">
      <div className="flex justify-center mt-6 md:mt-30 w-full md:w-1/2 p-6 md:p-0">
        <div className="flex flex-col justify-center w-full max-w-sm md:max-w-md lg:max-w-lg">
          <div className="flex justify-center">
            <img src={logo} alt="" />
          </div>
          <div className="mt-10 bg-white border border-gray-300 p-6 rounded-lg shadow-lg">
            <form onSubmit={handleSubmit}>
              <div>
                <label className="block text-sm font-normal leading-6 text-gray-900">
                  User Name
                </label>
                <input
                  id="username"
                  name="username"
                  type="text"
                  value={loginData.username}
                  onChange={handleChange}
                  required
                  className={`block w-full rounded-lg border ${
                    validationErrors.username
                      ? "border-red-500"
                      : "border-gray-300"
                  } p-1.5 text-gray-900 focus:border-sky-500 focus:outline-none h-12 sm:text-sm sm:leading-6`}
                />
                {validationErrors.username && (
                  <span className="text-sm text-red-600">
                    {validationErrors.username}
                  </span>
                )}
              </div>
              <div className="mt-5">
                <label className="block text-sm font-normal leading-6 text-gray-900">
                  Password
                </label>
                <input
                  name="password"
                  value={loginData.password}
                  onChange={handleChange}
                  type="password"
                  required
                  className={`block w-full rounded-lg border ${
                    validationErrors.password
                      ? "border-red-500"
                      : "border-gray-300"
                  } p-1.5 text-gray-900 focus:border-sky-500 focus:outline-none h-12 sm:text-sm sm:leading-6`}
                />
                {validationErrors.password && (
                  <span className="text-sm text-red-600">
                    {validationErrors.password}
                  </span>
                )}
              </div>
              <div className="mt-9">
                <button
                  type="submit"
                  disabled={status === "loading"}
                  className="flex w-full justify-center rounded-lg bg-gradient-to-r from-orange-300 to-pink-500 p-2 text-sm font-semibold leading-6 text-white shadow-sm hover:opacity-90 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 disabled:opacity-70"
                >
                  {status === "loading" ? "Logging in..." : "Login"}
                </button>
              </div>
            </form>
            <div className="flex gap-2 mt-8">
              <input type="checkbox" className="h-5 w-5" />
              <span className="font-normal text-sm text-gray-600">
                Remember Me
              </span>
            </div>
            <span className="text-gray-500 text-sm font-medium mt-24 text-center block">
              ©2024, All rights reserved
            </span>
          </div>
        </div>
      </div>
      <div className="hidden md:flex flex-col justify-between w-1/2 bg-white">
        <div className="px-14 2xl:px-24 mt-10 text-center">
          <h1 className="text-[#042D60] font-bold text-[2rem] leading-[normal]">
            Seamlessly manage all learner data in a unified platform.
          </h1>
          <p className="text-[#042D60] font-normal text-lg">
            Centralize customer data effortlessly. Streamline communication,
            sales, and support for seamless growth.
          </p>
        </div>
        <div className="relative mt-4">
          <div className="relative h-[32.5rem] lg:h-[33rem] xl:h-[30.5rem] w-full">
            <img src={curved} alt="" />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
